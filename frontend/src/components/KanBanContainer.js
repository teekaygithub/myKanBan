import React, {Component} from 'react';
import Ticket from './Ticket';
import '../App.css'

class KanBanContainer extends Component {
    constructor(props) {
        super(props);
        this.state = {
            tickets: [],
            project: {}
        }
    }

    componentDidMount() {
        fetch('http://localhost:8080/api/project/ticket/' + this.props.match.params.id)
            .then(res => res.json())
            .then(data => this.setState({tickets: data}))
            .catch(err => console.log(err));

        fetch('http://localhost:8080/api/project/' + this.props.match.params.id)
            .then(res => res.json())
            .then(data => this.setState({project: data}))
            .catch(err => console.log(err));
    }

    render () {

        const temp = this.state.tickets.map((el, index) => {
            return (
                <div key={index}>
                    <Ticket title={el.title} 
                        description={el.description}
                        status={el.status} />
                </div>
            );
        });

        if (this.state.tickets.length > 0) {
            return (
                <div className="container" id="kanban-container">
                    <h1>Project: {this.state.project.title}</h1>
                    {temp}
                </div>
            );
        } else {
            return (
                <div>
                    <h1>No ticket found for this project</h1>
                </div>
            );
        }
    }
}

export default KanBanContainer;