import React from 'react';
import {Link} from 'react-router-dom';

var ProjectCard = (props) => {
    const link = "/project/" + props.id;
    return (
        <div 
            className="card" 
            style={{minWidth: "300px", minHeight:"200px", textAlign:"center"}}>
            <h1>{props.title}</h1>
            <small>{props.description}</small>
            <Link to={link} className="btn stretched-link">Board View</Link>
        </div>
    );
}

export default ProjectCard;