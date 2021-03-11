var ticket = (props) => {
    return (
        <div>
            <h2>{props.title}</h2>
            <p>{props.description}</p>
            <p>Status: {props.status}</p>
        </div>
    );
}

export default ticket;