import React from 'react';

var ProjectCard = (props) => {
    return (
        <div className="card">
            <h1>{props.title}</h1>
            <p>{props.description}</p>
        </div>
    );
}

export default ProjectCard;