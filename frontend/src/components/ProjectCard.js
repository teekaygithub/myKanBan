import React from 'react';
import {Link} from 'react-router-dom';

var ProjectCard = (props) => {
    const link = "/project/" + props.id;
    return (
        <div className="card">
            <h1>{props.title}</h1>
            <p>{props.description}</p>
            <a href="#" className="btn stretched-link">Link</a>
            <Link to={link} >Kanban Board</Link>
        </div>
    );
}

export default ProjectCard;