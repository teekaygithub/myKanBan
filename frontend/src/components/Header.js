import {Link} from 'react-router-dom';

function Header (props) {

  const authMenu = (
    <>
      <li className='nav-item'>
        <Link to="/" className="nav-link">Home</Link>
      </li>
      <li className='nav-item'>
        <Link to="/projects" className="nav-link">Projects</Link>
      </li>
      <li className='nav-item'>
        <a href="" onClick={props.logoutHandler}  className="nav-link">Log Out</a>
      </li>
    </>);

  const nonAuthMenu = (
    <>
      <li className='nav-item'>
        <Link to="/" className="nav-link">Home</Link>
      </li>
      <li className='nav-item'>
        <Link to="/" className="nav-link">Register</Link>
      </li>
      <li className='nav-item'>
        <Link to="/login" className="nav-link">Log In</Link>
      </li>
    </>);

  return (
    <nav className="navbar navbar-expand-sm bg-dark">
      <ul className="navbar-nav">
        {props.loggedIn ? authMenu: nonAuthMenu}
      </ul>
    </nav>
  );
}

export default Header;