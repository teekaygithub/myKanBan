import {Link} from 'react-router-dom';

function Header (props) {
  if (props.loggedIn) {
    return (
      <nav className="navbar navbar-expand-sm bg-dark">
        <ul className='navbar-nav'>
          <li className='nav-item'>
            <Link to="/" className="nav-link">Home</Link>
          </li>
          <li className='nav-item'>
            <Link to="/projects" className="nav-link">Projects</Link>
          </li>
          <li className='nav-item'>
            <a href="" onClick={props.logoutHandler}  className="nav-link">Log Out</a>
          </li>
        </ul>
      </nav>
    );
  } else {
    return (
      <nav className="navbar navbar-expand-sm bg-dark">
        <ul className='navbar-nav'>
          <li className='nav-item'>
            <Link to="/" className="nav-link">Home</Link>
          </li>
          <li className='nav-item'>
            <Link to="/" className="nav-link">Register</Link>
          </li>
          <li className='nav-item'>
            <Link to="/login" className="nav-link">Log In</Link>
          </li>
        </ul>
      </nav>
    );
  }
}

export default Header;