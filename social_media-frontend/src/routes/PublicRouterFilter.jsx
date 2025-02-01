import { Navigate, Outlet } from "react-router-dom";
import { useAuthContext } from "../context/AuthContext";

export default function PublicRouterFilter() {

  const { isAuthenticated } = useAuthContext()
  console.log('is authenticated: ', isAuthenticated)

  return isAuthenticated ? <Navigate to='/' /> : <Outlet/>
}