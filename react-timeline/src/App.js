import React from "react";
import { BrowserRouter as Router, Route,Redirect ,Switch,Link  } from "react-router-dom";
import MyComponent from './mycomponents/MyComponent2'
import ReactGA from 'react-ga';

ReactGA.initialize('UA-11039602-8');

export default function App() {
  return <MyComponent/>
}