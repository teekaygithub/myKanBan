import {Link} from 'react-router-dom';

function Header () {
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
              <Link to="/">Log In</Link>
            </li>
          </ul>
        </nav>
    );
}

export default Header;