import React, {Component} from 'react';
import Column from './Column';
import AddTicket from './AddTicket';
import '../App.css'
import {API_ONEPROJECT, API_ALLTICKETFORPROJECT} from '../constants'

class KanBanContainer extends Component {
    constructor(props) {
        super(props);
        this.state = {
            tickets: [],
            project: {}
        }
    }

    componentDidMount() {
        fetch(API_ALLTICKETFORPROJECT + this.props.match.params.id)
            .then(res => res.json())
            .then(data => this.setState({tickets: data}))
            .catch(err => console.log(err));

        fetch(API_ONEPROJECT + this.props.match.params.id)
            .then(res => res.json())
            .then(data => this.setState({project: data}))
            .catch(err => console.log(err));
    }

    render () {

        if (this.state.tickets.length > 0) {
            return (
                <div className="container" id="kanban-container">
                    <div className="d-flex justify-content-around">
                        <h1>
                            Project: {this.state.project.title}
                        </h1>
                        <AddTicket {...this.props} project={this.state.project} />
                    </div>
                    <div className="column-container row my-5" >
                        <Column tickets={this.state.tickets} title="TODO" />
                        <Column tickets={this.state.tickets} title="INPROGRESS" />
                        <Column tickets={this.state.tickets} title="DONE" />
                    </div>
                </div>
            );
        } else {
            return (
                <div 
                    className="d-flex justify-content-around mx-auto"
                    id="kanban-container" >
                    <h1 className="my-auto" >
                        No ticket found for this project
                    </h1>
                    <AddTicket {...this.props} project={this.state.project} />
                </div>
            );
        }
    }
}

export default KanBanContainer;