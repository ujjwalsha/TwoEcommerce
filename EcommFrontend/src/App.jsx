import React, { useState } from 'react'
import {BrowserRouter as Router, Route, Routes } from 'react-router-dom'
import './App.css'
import Home from './Components/Home'
import Contact from './Components/Contact'
import axios from 'axios'
import About from './Components/About'
import Auth from './Components/Auth'
import ProductPage from './Components/ProductPage'
import { LoaderIcon } from 'react-hot-toast'

function App() {

  const [Location, setLocation] = useState([]);

    const handleLocation = () =>{
        navigator.geolocation.getCurrentPosition((pos) =>{
            const {latitude, longitude} = pos.coords;
            console.log(latitude, longitude);

            const url = `https://nominatim.openstreetmap.org/reverse?format=json&lat=${latitude}&lon=${longitude}`;
            
            axios.get(url)
            .then(response =>{
                // console.log(response.data.address);
                setLocation(response.data);
            })
            .catch(error =>{
                console.log(error);      
            })
        
        })
    }

  return (
    <Router>
      <Routes>
        <Route path='/' element= {<Home handleLocation={handleLocation} Location={Location} />}/>
        <Route path='/contact' element = {<Contact handleLocation={handleLocation} Location={Location} />}/>
        <Route path='/About' element = {<About handleLocation={handleLocation} Location={Location} />}/>
        <Route path='/Auth' element = {<Auth handleLocation={handleLocation} Location={Location}/>}/>
      </Routes>
    </Router>
  )
}

export default App
