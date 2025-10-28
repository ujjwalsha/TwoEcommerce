import React from 'react'
import Navbar from './Navbar'

function About({handleLocation, Location}) {
  return (
    <div className='about-section'>
        <Navbar handleLocation={handleLocation} Location={Location}/>
        <p>this is about section</p>
    </div>
  )
}

export default About