import React, {Component} from 'react';
import Ticket from './Ticket';

class Column extends Component {
    constructor(props) {
        super(props);
        this.state = {
            tickets: []
        }
        this.allowDrop = this.allowDrop.bind(this);
        this.handleDrop = this.handleDrop.bind(this);
    }

    componentDidMount() {
        const filtered = this.props.tickets.filter(ticket => {
            return ticket.status === this.props.title;
        });
        this.setState({
            tickets: filtered
        });
    }

    allowDrop(e) {
        e.preventDefault();
    }

    handleDrop(e) {
        e.preventDefault();
        let data = e.dataTransfer.getData("Text");
        e.target.appendChild(document.getElementById(data));
    }

    render () {
        const ticketComponents = this.state.tickets.length > 0 ? 
            this.state.tickets.map((ticket, index) => (
                <Ticket 
                    key={index}
                    title={ticket.title} 
                    description={ticket.description} 
                    status={ticket.status}
                    id={index} />
            )) : null;
        return (
            <div 
                className="column"
                onDragOver={this.allowDrop}
                onDrop={this.handleDrop} >
                <h3>{this.props.title}</h3>
                {ticketComponents}
            </div>
        );
    }
}

export default Column;