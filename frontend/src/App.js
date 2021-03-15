import './App.css';
import Header from "./components/Header";
import ProjectContainer from "./components/ProjectContainer";
import KanBanContainer from "./components/KanBanContainer";
import {
  Switch,
  Route,
} from 'react-router-dom'
import Welcome from './components/Welcome';

function App() {
  return (
      <div className="App">
        <Header />

        <Switch>
          <Route exact path="/">
            <Welcome />
          </Route>
          <Route exact path = "/project/:id" component={KanBanContainer} />
          <Route path="/projects">
            <ProjectContainer />
          </Route>
          <Route path="/about">
            <About />
          </Route>
        </Switch>
      </div>
  );
}

function About () {
  return (
    <div>
      <h1>About</h1>
    </div>
  );
};

export default App;
