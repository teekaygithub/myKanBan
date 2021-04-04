import React, {Component} from 'react';
import { API_ONETICKET } from '../constants';
import Ticket from './Ticket';

class Column extends Component {
    constructor(props) {
        super(props);
        this.state = {
            tickets: []
        }
        this.allowDrop = this.allowDrop.bind(this);
        this.handleDrop = this.handleDrop.bind(this);
        this.changeTicketStatus = this.changeTicketStatus.bind(this);
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

    async handleDrop(e) {
        e.preventDefault();
        let data = e.dataTransfer.getData("text/plain");
        let draggingTicket = JSON.parse(data);
        if (e.target.className==="column" && await this.changeTicketStatus(draggingTicket)===true) {
            e.target.appendChild(document.getElementById(draggingTicket.ticketid));
        }
    }

    async changeTicketStatus(ticketProps) {
        ticketProps.status = this.props.title;
        console.log(`PUT to ${API_ONETICKET + ticketProps.ticketid}`);
        const res = await fetch(API_ONETICKET + ticketProps.ticketid, {
            method: "PUT",
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(ticketProps),
        });
        return res.status == 200 ? true : false;
    }

    render () {
        const ticketComponents = this.state.tickets.length > 0 ? 
            this.state.tickets.map((ticket, index) => (
                <Ticket 
                    key={index}
                    title={ticket.title} 
                    description={ticket.description} 
                    status={ticket.status}
                    ticketid={ticket.id}
                    projectId={ticket.projectId} />
            )) : null;
        return (
            <div 
                className="column"
                onDragOver={this.allowDrop}
                onDrop={this.handleDrop} >
                <h3 className="mx-auto py-2">
                    {this.props.title}
                </h3>
                {ticketComponents}
            </div>
        );
    }
}

export default Column;