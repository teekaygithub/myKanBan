import {Link} from 'react-router-dom';

function Footer() {
    return (
        <footer>
            <div id="footer-socials">
                <p>TBD: Social Media Links</p>
            </div>
            <div id="footer-links">
                <Link to="/about">About</Link><br />
                <Link to="/">Contact</Link>
            </div>
        </footer>
    );
}

export default Footer;