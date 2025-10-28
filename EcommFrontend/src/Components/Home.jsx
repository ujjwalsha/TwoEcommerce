
import React, { useEffect, useState } from 'react'

import Navbar from './Navbar';
import axios from 'axios'

function Home({handleLocation, Location}) {

    const [Category, setCategory] = useState([]);

    useEffect(()=>{

        const url = 'http://localhost:8080/api/categories';

        axios.get(url)
        .then(response =>{
            console.log(response.data);
            
        })
        .catch(error =>{
            console.log(error);
        })
    },[])

    

    return (
        <div className='Home-container'>
            <Navbar handleLocation={handleLocation} Location={Location} />
            
        </div>
    )
}

export default Home