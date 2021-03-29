import React from 'react';
import {Link} from 'react-router-dom';

var ProjectCard = (props) => {
    const link = "/project/" + props.id;
    return (
        <div 
            className="card my-3"
            style={{maxWidth: "33%", textAlign: "center"}} >
            <Link 
                to={link} 
                className="btn btn-info"
                style={{height: "100px", fontSize: "1.5rem"}} >
                    {props.title}
            </Link>
        </div>
    );
}

export default ProjectCard;