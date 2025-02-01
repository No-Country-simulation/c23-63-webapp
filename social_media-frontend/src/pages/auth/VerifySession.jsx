import { useEffect } from "react"
import { useNavigate } from "react-router-dom"
import axiosApi from "../../services/axiosApi"
import { useAuthContext } from "../../context/AuthContext"

export default function VerifySession () {
  const navigate = useNavigate()
  const { login } = useAuthContext()
  useEffect(()=>{
    const verifySession = async () => {
      try {
        const {data} = await axiosApi.get('/auht/current-user')
        if (data) {
          console.log("ejec login()")
          login()
          navigate('/')
        }
      } catch {
        navigate('/auth/login')
      }
    }
    verifySession()
  },[navigate, login])
  return (
    <>
      <div>
        cargando...
      </div>
    </>
  )
}