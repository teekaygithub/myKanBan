import React, {Component} from 'react';

class Ticket extends Component {
    constructor(props) {
        super(props);
        this.handleDrag = this.handleDrag.bind(this);
    }

    handleDrag(e) {
        e.dataTransfer.setData("Text", e.target.id);
    }

    render () {
        return (
            <div
                className="card ticket mx-auto my-2 px-1 py-1"
                draggable={true}
                onDragStart={this.handleDrag}
                id={this.props.ticketid} >
                <span><strong>{this.props.title}</strong></span>
                <span>{this.props.description} </span>
                <span>Status: {this.props.status}</span>
            </div>
        );
    }
}

export default Ticket;