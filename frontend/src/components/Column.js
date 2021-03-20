const ticketList = (tickets) => (
    <>
        {
            tickets.map((ticket, index) => (
                <div key={index} className="card ticket mx-auto my-2 px-1 py-1">
                    <h4>{ticket.title}</h4>
                    <small>{ticket.description}</small>
                </div>
            ))
        }
    </>
)

const Column = (props) => {
    const temp = props.tickets.filter(ticket => {
        return ticket.status === props.title;
    })
    return (
        <div className="column" >
            <h3>{props.title}</h3>
            {ticketList(temp)}
        </div>
    );
}

export default Column;