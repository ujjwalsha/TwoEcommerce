import React, { useState } from 'react'
import Navbar from './Navbar'
import axios from 'axios';
import toast, {Toaster} from 'react-hot-toast'

function Auth({handleLocation, Location}) {

    const [username, setUsername] = useState("")
    const [password, setpassword] = useState("")



    const handleSubmit = async(e) =>{
        e.preventDefault();
        console.log(username, password);
        
        try{
            const res = await axios.post("http://localhost:8080/api/users/login",
                {username, password},
                {withCredentials : true,
                    headers: {
                        "Content-Type": "application/json"
                    },
                }
            )
            
            toast.success('Logged in successfully!')
            console.log(res.data);
            
        }
        catch(error)
        {
            console.error(error);
            toast.error("This didn't work.")
            
        }

        setUsername("");
        setpassword("")
    }


  return (
    <div className='Auth-container'>
        <Navbar handleLocation={handleLocation} Location={Location} />
        <div className='login-container flex mt-20 justify-center'>
       
            <form className='flex p-2 flex-col border gap-2 '>
                <input 
                    type="text" 
                    placeholder='Enter username'
                    className='p-2 border w-xs font-semibold'
                    required
                    onChange={(e) => setUsername(e.target.value)}
                />
                <input 
                    type="password" 
                    placeholder='Enter password' 
                    className='p-2 border w-xs font-semibold'
                    required
                    onChange={(e) => setpassword(e.target.value)}
                />
                <div className='flex  justify-end'>
                    <a href="">Forget Password!</a>
                </div>
               
                <button 
                    className='bg-white text-black font-semibold cursor-pointer p-2 hover:border'
                    onClick={handleSubmit}
                    type='submit'
                >Submit</button>
                <Toaster
                    position="top-center"
                    reverseOrder={false}
                />

                <div className='flex justify-center'>
                    <p>Don't have an account: Sign Up</p> 
                </div>
                
            </form>
            

        </div>
    </div>
  )
}

export default Auth