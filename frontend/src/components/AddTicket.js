import React, {Component} from 'react';
import Modal from 'react-modal';

class AddTicket extends Component {
    constructor(props) {
        super(props);
        this.state = {
            title: "",
            description: "",
            openModal: false,
        }
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleOpenModal = this.handleOpenModal.bind(this);
        this.handleCloseModal = this.handleCloseModal.bind(this);
    }

    handleOpenModal() {
        this.setState({
            openModal: true,
        })
    }

    handleCloseModal() {
        this.setState({
            openModal: false,
        })
    }

    handleChange(e) {
        this.setState({
            [e.target.name] : e.target.value,
        });
    }

    async handleSubmit(e) {
        e.preventDefault();
        const newTicket = {
            title: this.state.title,
            description: this.state.description,
            projectId: this.props.project.id,
            status: 'TODO',
        }
        const res = await fetch('http://localhost:8080/api/addticket', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(newTicket),
        })

        if (res.status && res.status === 201) {
            console.log(`Pushing to /project/${this.props.match.params.id}`);
            this.props.history.push("/project/" + this.props.match.params.id);
        } else {
            console.log(`POST request failed: ${res.status}`);
        }
    }

    render () {
        return (
            <div className="container-fluid w-50 my-3">
                <button
                    type="button"
                    className="btn btn-primary"
                    onClick={this.handleOpenModal} >
                        +ADD TICKET
                </button>
                <Modal
                    isOpen={this.state.openModal}
                    onRequestClose={this.handleCloseModal} >
                    <h2>Create a new ticket for project {this.props.project.title}</h2>
                    <form>
                        <div className="form-group">
                            <label>Title</label>
                            <input 
                                type="text" 
                                name="title"
                                className="form-control"
                                onChange={this.handleChange}></input>
                        </div>
                        <div className="form-group">
                            <label>Description</label>
                            <input 
                                type="text"
                                name="description"
                                className="form-control"
                                onChange={this.handleChange}></input>
                        </div>
                        <button 
                            type="submit" 
                            className="btn btn-primary"
                            onClick={this.handleSubmit}>Submit</button>
                    </form>
                </Modal>
            </div>
        );
    }
}

export default AddTicket;