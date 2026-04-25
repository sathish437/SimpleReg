import { useState } from "react"
import { register } from "./services/authService"
import { useNavigate } from "react-router-dom"

function Reg(){
  const navigate = useNavigate()
  const [userName,setUserName] = useState("")
  const [emailId,setEmailId] = useState("")
  const [password,setPassword] = useState("")
  const [confirmPassword,setConfirmPassword] = useState("")
  const [role,setRole] = useState("USER")
  const [check,setCheck] = useState("")
  const [loading,setLoading] = useState(false)

  const succesful = async () => {
    if(!emailId.endsWith("@gmail.com")){
      alert(`Enter a valid email`)
      return
    }
    if(check===""){
      alert("Please accept the terms and conditions")
      return
    }
    if(password!==confirmPassword){
      alert("Passwords do not match")
      return
    }
    if(password.length < 6){
      alert("Password must be at least 6 characters")
      return
    }

    setLoading(true)
    
    try {
      // Call backend API
      const regData = {
        userName: userName,
        emailId: emailId,
        role: role,
        password: password
      }
      
      const response = await register(regData)
      
      alert(`Registration successful! Welcome ${response.userName}`)
      navigate("/login")
    } catch (error) {
      alert(`Registration failed: ${error.message}`)
    } finally {
      setLoading(false)
    }
  }

  return(
    <>
    <main className='flex min-h-screen w-full bg-blue-600 justify-center items-center p-4 sm:p-6 lg:p-8'>
      <section className='rounded-3xl p-4 sm:p-6 lg:p-8 bg-white w-full max-w-[400px] shadow-2xl'>
          <h1 className='flex text-xl sm:text-2xl font-bold justify-center'><p className='underline'>Re</p>gistration</h1>
          <div className='flex flex-col gap-3 mt-6 sm:mt-10'>
            <input name="userName" onChange={(e)=>setUserName(e.target.value)} className='p-3 border-b-2 rounded-[5px] border-gray-500 w-full focus:outline-none focus:border-blue-600 transition-colors' type="text" placeholder="Enter your user name" />
            <input name="emailId" onChange={(e)=>setEmailId(e.target.value)} className='p-3 border-b-2 rounded-[5px] border-gray-500 w-full focus:outline-none focus:border-blue-600 transition-colors' type='email' placeholder="Enter your email id" />
            <select name="role" onChange={(e)=>setRole(e.target.value)} className='p-3 border-b-2 rounded-[5px] border-gray-500 bg-white w-full focus:outline-none focus:border-blue-600 transition-colors'>
              <option value="USER">USER</option>
              <option value="ADMIN">ADMIN</option>
            </select>
            <input name="password" onChange={(e)=>setPassword(e.target.value)} className='p-3 border-b-2 rounded-[5px] border-gray-500 w-full focus:outline-none focus:border-blue-600 transition-colors' type="password" placeholder="Create a password" />
            <input name="confirmPassword" onChange={(e)=>setConfirmPassword(e.target.value)} className='p-3 border-b-2 rounded-[5px] border-gray-500 w-full focus:outline-none focus:border-blue-600 transition-colors' type="password" placeholder="Confirm password" />
          </div>
          <nav className='flex items-center mt-4 gap-2'>
            <input id="terms" onChange={(e)=>setCheck(e.target.checked ? "checked" : "")} className='w-4 h-4 cursor-pointer' type='checkbox'/>
            <label htmlFor="terms" className='text-gray-600 text-sm sm:text-base cursor-pointer select-none'>I accept all terms & conditions</label>
          </nav>
          <button onClick={succesful} disabled={loading} className={`w-full active:scale-95 hover:bg-blue-700 transition-all mt-4 p-3 rounded-2xl text-white font-medium ${loading ? 'bg-gray-400 cursor-not-allowed' : 'bg-blue-600'}`}>
            {loading ? 'Registering...' : 'Register Now'}
          </button>
          <p className='flex justify-center mt-3 text-sm sm:text-base flex-wrap gap-1'>Already have account?<a className='text-blue-600 font-semibold hover:underline' href="/login">Login now</a></p>
      </section>
    </main>
    </>
  )
}
export default Reg