import React from 'react';
import ProjectCard from './ProjectCard';

class ProjectContainer extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            projects: []
        }
    }

    componentDidMount() {
        fetch('http://localhost:8080/api/all')
            .then(resp => resp.json())
            .then(data => this.setState({projects: data}))
            .catch(err => console.log(err));
    }

    render () {

        const projectElem = this.state.projects.map((el, index) => {
            return (
                <div key={index}>
                    <ProjectCard title={el.title} description={el.description} id={el.id} />
                </div>
            );
        })

        if (this.state.projects.length > 0) {
            return (
                <div className="container card-deck">
                    {projectElem}
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