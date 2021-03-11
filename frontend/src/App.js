import './App.css';
import ProjectContainer from "./components/ProjectContainer";
// import KanBanContainer from "./components/KanBanContainer";
import {
  BrowserRouter,
  Switch,
  Route,
  Link
} from 'react-router-dom'

function App() {
  return (
      <div className="App">
        <BrowserRouter>
          <nav>
            <ul>
              <li>
                <Link to="/">Home</Link>
              </li>
              <li>
                <Link to="/project">Project</Link>
              </li>
              <li>
                <Link to="/about">About</Link>
              </li>
            </ul>
          </nav>

          <Switch>
            <Route exact path="/">
              <Home />
            </Route>
            <Route path="/project">
              <ProjectContainer />
            </Route>
            <Route path="/about">
              <About />
            </Route>
          </Switch>
        </BrowserRouter>
      </div>
  );
}

function Home () {
  return (
    <div>
      <h1>Home</h1>
    </div>
  );
};

function About () {
  return (
    <div>
      <h1>About</h1>
    </div>
  );
};

export default App;
