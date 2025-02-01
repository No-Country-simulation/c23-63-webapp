import { useState } from "react"
import { AuthContext } from "./AuthContext"

const AuthProvider = ({children}) => {

  const [isAuthenticated, setIsAuthenticated] = useState(false)
  const login = () => {
    console.log("login")
    setIsAuthenticated(true)
  }
  const logout = () => setIsAuthenticated(false)

  return (
    <AuthContext.Provider value={{isAuthenticated, login, logout}}>
      {children}
    </AuthContext.Provider>
  )
}

export default AuthProvider