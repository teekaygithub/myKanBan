import {Link} from 'react-router-dom';

function Header (props) {
  if (props.loggedIn) {
    return (
      <nav className="navbar bg-dark">
        <ul className='navbar-nav'>
          <li className='nav-item'>
            <Link to="/">Home</Link>
          </li>
          <li className='nav-item'>
            <Link to="/showprojects">Projects</Link>
          </li>
          <li className='nav-item'>
            <a href="" onClick={props.logoutHandler}>Log Out</a>
          </li>
        </ul>
      </nav>
    );
  } else {
    return (
      <nav className="navbar bg-dark">
        <ul className='navbar-nav'>
          <li className='nav-item'>
            <Link to="/">Home</Link>
          </li>
          <li className='nav-item'>
            <Link to="/">Register</Link>
          </li>
          <li className='nav-item'>
            <Link to="/login">Log In</Link>
          </li>
        </ul>
      </nav>
    );
  }
}

export default Header;