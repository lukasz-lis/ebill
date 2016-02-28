const React = require('react');
const client = require('./client');


class App extends React.Component {

    constructor(props) {
        super(props);
        this.state = {members: []};
    }

    componentDidMount() {
        client({method: 'GET', path: '/api/members'}).done(response => {
            this.setState({members: response.entity._embedded.members});
        });
    }

    render() {
        return (
            <MemberList members={this.state.members}/>
    )
    }
}

class MemberList extends React.Component{
    render() {
        var members = this.props.members.map(member =>
            <Member key={member._links.self.href} member={member}/>
    );
        return (
            <table>
                <tr>

                    <th>First Name</th>
                    <th>Last Name</th>
                </tr>
                {members}
            </table>
    )
    }
}

class Member extends React.Component{
    render() {
        return (
            <tr>
                <td>{this.props.member.firstName}</td>
                <td>{this.props.member.lastName}</td>
            </tr>
    )
    }
}

React.render(
<App />,
    document.getElementById('react')
)