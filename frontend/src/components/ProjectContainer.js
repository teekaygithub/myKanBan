import React from 'react';
import ProjectCard from './ProjectCard';
import {Link} from 'react-router-dom';
import { API_ALLPROJECTS } from '../constants';

class ProjectContainer extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            projects: []
        }
    }

    componentDidMount() {
        fetch(API_ALLPROJECTS)
            .then(resp => resp.json())
            .then(data => this.setState({projects: data}))
            .catch(err => console.log(err));
    }

    render () {

        const projectElem = this.state.projects.map((el, index) => {
            return (
                <div key={index}>
                    <ProjectCard 
                        title={el.title} 
                        description={el.description} 
                        id={el.id}
                        className="my-auto" />
                </div>
            );
        })

        if (this.state.projects.length > 0) {
            return (
                <div className="container">
                    <div className="d-flex">
                        <h1 
                            className="my-3"
                            style={{textAlign:'center'}}>
                            Your Active Projects
                        </h1>
                        <div className="d-flex justify-content-end w-75 my-4">
                            <Link to="/addproject" className="mr-0 my-auto">
                                <button className="btn btn-primary">+NEW PROJECT</button>
                            </Link>
                        </div>
                    </div>
                    <div 
                        className="container-fluid 
                                    card-deck 
                                    py-5 
                                    justify-content-center
                                    w-100
                                    mx-auto">
                        {projectElem}
                    </div>
                </div>
            );
        } else {
            return (
                <div className="container">
                    <h1>Let's get started with a project!</h1>
                    <button>Get Started</button>
                </div>
            );
        }
    }
}

export default ProjectContainer;