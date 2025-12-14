import React from 'react'
import Navbar from './Navbar'
import Footer from './Footer'

function Contact({handleLocation, Location}) {
  return (
    <div className='contact-container ' >
        <Navbar handleLocation={handleLocation} Location={Location} />
        <div className='detail flex flex-col justify-center mt-10 items-center'>

            <form className='contact flex flex-col border p-3 gap-2'>
                <input 
                    type="text" 
                    className='p-2 border w-sm'
                    placeholder='Enter your name'
                />
                <input 
                    type="text"
                    className='p-2 border'
                    placeholder='Enter your Email'  
                />

                <textarea 
                    name="" id=""
                    className='p-2 w-full h-40 border'
                    placeholder='Message'
                ></textarea>

                <button 
                    className='p-2 bg-white font-semibold  hover:border text-black rounded-xl cursor-pointer'
                >Submit</button>
            </form>
           

        </div>

        <Footer/>

    </div>
  )
}

export default Contact