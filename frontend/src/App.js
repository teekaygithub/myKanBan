import './App.css';
import Header from "./components/Header";
import ProjectContainer from "./components/ProjectContainer";
// import KanBanContainer from "./components/KanBanContainer";
import {
  Switch,
  Route,
} from 'react-router-dom'

function App() {
  return (
      <div className="App">
        <Header />

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
