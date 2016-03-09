const React = require('react');
const when = require('when');

const client = require('./client');

const follow = require('./follow');

const root = '/api';


class App extends React.Component {

    constructor(props) {
        super(props);
        this.state = {members: [], attributes: [], pageSize: 2, links: {}};
        this.onCreate = this.onCreate.bind(this);
        this.onDelete = this.onDelete.bind(this);
        this.onNavigate = this.onNavigate.bind(this);
        this.updatePageSize = this.updatePageSize.bind(this);
    }

    componentDidMount() {
        this.loadFromServer(this.state.pageSize);
    }

    loadFromServer(pageSize) {
        follow(client, root, [
            {rel: 'members', params: {size: pageSize}}]
        ).then(memberCollection => {
            return client({
                method: 'GET',
                path: memberCollection.entity._links.profile.href,
                headers: {'Accept': 'application/schema+json'}
            }).then(schema => {
                this.schema = schema.entity;
                this.links = memberCollection.entity._links;
                return memberCollection;
            });
        }).then(memberCollection => {
            return memberCollection.entity._embedded.members.map(member =>
                client({
                    method: 'GET',
                    path: member._links.self.href
                })
            );
        }).then(memberPromises => {
            return when.all(memberPromises);
        }).done(memberCollection => {
            this.setState({
                members: memberCollection.entity._embedded.members,
                attributes: Object.keys(this.schema.properties),
                pageSize: pageSize,
                links: memberCollection.entity._links
            });
        });
    }

    updatePageSize(pageSize) {
        if (pageSize !== this.state.pageSize) {
            this.loadFromServer(pageSize);
        }
    }

    onDelete(member) {
        client({method: 'DELETE', path: member._links.self.href}).done(response => {
            this.loadFromServer(this.state.pageSize);
        });
    }

    onCreate(newMember) {
        follow(client, root, ['members']).then(memberCollection => {
            return client({
                method: 'POST',
                path: memberCollection.entity._links.self.href,
                entity: newMember,
                headers: {'Content-Type': 'application/json'}
            })
        }).then(response => {
            return follow(client, root, [
                {rel: 'members', params: {'size': this.state.pageSize}}]);
        }).done(response => {
            this.onNavigate(response.entity._links.last.href);
        });
    }

    onNavigate(navUri) {
        client({method: 'GET', path: navUri}).done(memberCollection => {
            this.setState({
                members: memberCollection.entity._embedded.members,
                attributes: this.state.attributes,
                pageSize: this.state.pageSize,
                links: memberCollection.entity._links
            });
        });
    }

    render() {
        return (
            <div>
                <CreateDialog attributes={this.state.attributes} onCreate={this.onCreate}/>
                <MemberList members={this.state.members}
                            links={this.state.links}
                            pageSize={this.state.pageSize}
                            attributes={this.state.attributes}
                            onNavigate={this.onNavigate}
                            onDelete={this.onDelete}
                            updatePageSize={this.updatePageSize}/>
            </div>
        )
    }
}

class MemberList extends React.Component {

    constructor(props) {
        super(props);
        this.handleNavFirst = this.handleNavFirst.bind(this);
        this.handleNavPrev = this.handleNavPrev.bind(this);
        this.handleNavNext = this.handleNavNext.bind(this);
        this.handleNavLast = this.handleNavLast.bind(this);
        this.handleInput = this.handleInput.bind(this);
    }

    handleInput(e) {
        e.preventDefault();
        var pageSize = React.findDOMNode(this.refs.pageSize).value;
        if (/^[0-9]+$/.test(pageSize)) {
            this.props.updatePageSize(pageSize);
        } else {
            React.findDOMNode(this.refs.pageSize).value = pageSize.substring(0, pageSize.length - 1);
        }
    }

    handleNavFirst(e) {
        e.preventDefault();
        this.props.onNavigate(this.props.links.first.href);
    }

    handleNavPrev(e) {
        e.preventDefault();
        this.props.onNavigate(this.props.links.prev.href);
    }

    handleNavNext(e) {
        e.preventDefault();
        this.props.onNavigate(this.props.links.next.href);
    }

    handleNavLast(e) {
        e.preventDefault();
        this.props.onNavigate(this.props.links.last.href);
    }

    render() {
        var members = this.props.members.map(member =>
            <Member key={member._links.self.href} member={member} attributes={this.props.attributes}
                    onDelete={this.props.onDelete}/>
        );

        var navLinks = [];
        if ("first" in this.props.links) {
            navLinks.push(<button key="first" onClick={this.handleNavFirst}>&lt;&lt;</button>);
        }
        if ("prev" in this.props.links) {
            navLinks.push(<button key="prev" onClick={this.handleNavPrev}>&lt;</button>);
        }
        if ("next" in this.props.links) {
            navLinks.push(<button key="next" onClick={this.handleNavNext}>&gt;</button>);
        }
        if ("last" in this.props.links) {
            navLinks.push(<button key="last" onClick={this.handleNavLast}>&gt;&gt;</button>);
        }

        return (
            <div>
                <input ref="pageSize" defaultValue={this.props.pageSize} onInput={this.handleInput}/>
                <table>
                    <tr>

                        <th>First Name</th>
                        <th>Last Name</th>
                    </tr>
                    {members}
                </table>
                <div>
                    {navLinks}
                </div>
            </div>
        )
    }
}

class Member extends React.Component {

    constructor(props) {
        super(props);
        this.handleDelete = this.handleDelete.bind(this);
    }

    handleDelete() {
        this.props.onDelete(this.props.member);
    }


    render() {
        return (
            <tr>
                <td>{this.props.member.firstName}</td>
                <td>{this.props.member.lastName}</td>
                <td>
                    <button onClick={this.handleDelete}>Delete</button>
                </td>
            </tr>
        )
    }
}

class CreateDialog extends React.Component {

    constructor(props) {
        super(props);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleSubmit(e) {
        e.preventDefault();
        var newMember = {};
        this.props.attributes.forEach(attribute => {
            newMember[attribute] = React.findDOMNode(this.refs[attribute]).value.trim();
        });
        this.props.onCreate(newMember);
        this.props.attributes.forEach(attribute => {
            React.findDOMNode(this.refs[attribute]).value = ''; // clear out the dialog's inputs
        });
        window.location = "#";
    }

    render() {
        var inputs = this.props.attributes.map(attribute =>
            <p key={attribute}>
                <input type="text" placeholder={attribute} ref={attribute} className="field"/>
            </p>
        );
        return (
            <div>
                <a href="#createMember">Create</a>

                <div id="createMember" className="modalDialog">
                    <div>
                        <a href="#" title="Close" className="close">X</a>

                        <h2>Create new employee</h2>

                        <form>
                            {inputs}
                            <button onClick={this.handleSubmit}>Create</button>
                        </form>
                    </div>
                </div>
            </div>
        )
    }
}

React.render(
    <App />,
    document.getElementById('react')
);



