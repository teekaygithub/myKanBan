import React, {Component} from 'react';
import { API_ADDPROJECT } from '../constants';

class AddProject extends Component {
    constructor(props) {
        super(props);
        this.state = {
            title: "",
            description: ""
        }
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleChange(e) {
        this.setState({
            [e.target.name] : e.target.value,
        });
    }

    async handleSubmit(e) {
        e.preventDefault()

        const res = await fetch(API_ADDPROJECT, {
            method: 'POST',
            headers: {
                'Content-Type' : 'application/json'
            },
            body: JSON.stringify(this.state),
        });

        if (res.status && res.status === 201) {
            this.props.history.push("/projects");
        } else {
            console.log(`Bad request, status: ${res.status}`);
        }
    }

    render() {
        return (
            <div className="container-fluid w-50 my-3">
                <h2>Create a new project</h2>
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
            </div>
        );
    }
}

export default AddProject;