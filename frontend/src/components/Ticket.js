var ticket = (props) => {
    return (
        <div>
            <span>{props.title} </span>
            <span>{props.description} </span>
            <span>Status: {props.status}</span>
        </div>
    );
}

export default ticket;