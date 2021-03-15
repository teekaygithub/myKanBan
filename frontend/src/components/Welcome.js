import welcomeImage from '../images/welcome-image.jpg';
import kanbanBoard from "../images/postit-notes.jpg";

let Welcome = () => {
    return (
        <div id="welcome-container" >
            <div id="welcome-banner" >
                <h1>Welcome to MyKanBan!</h1>
                <p>Your very own KanBan board to keep track of your projects</p>
            </div>
            <section id="section-welcome" >
                <img src={welcomeImage} alt="Kanban board example"></img>
            </section>
            <section id="section-feature-kanban" >
                <div>
                    <h3>Visualize your project as a Kanban Board</h3>
                </div>
                <div>
                    <img src={kanbanBoard} alt="Kanban board"></img>
                </div>
            </section>
            <section id="section-feature-ticket">
                <div>
                    <img src={kanbanBoard} alt="Ticket example"></img>
                </div>
                <div>
                    <h3>Write detailed tickets that capture your effort</h3>
                </div>
            </section>
        </div>
    );
}

export default Welcome;