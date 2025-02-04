import { Route } from "react-router-dom";
import PageAuth from "../pages/auth/PageAuth";
import Login from "../pages/auth/Login";
import Register from "../pages/auth/Register";
import PasswordRecoveryRequest from "../pages/auth/PasswordRecoveryRequest";
import PasswordResetConfirmation from "../pages/auth/PasswordResetConfirmation";
import PublicRouterFilter from "./PublicRouterFilter";
import Page404 from "../pages/Page404";
import PageProfile from "../pages/Profile/PageProfile";
import PageHome from "../pages/home/PageHome";

import VerifySession from "../pages/auth/VerifySession";

export function PublicRoutes() {
  return (
    <>
      <Route element={<PublicRouterFilter/>}>
        <Route path='/auth'  element={<PageAuth />}>
          <Route path='login' element={<Login />}/>
          <Route path='register' element={<Register />}/>
          <Route path='password-recovery' element={<PasswordRecoveryRequest />}/>
          <Route path='password-reset' element={<PasswordResetConfirmation />}/>
          <Route path='verify-session' element={<VerifySession/>}/>
        </Route>
      </Route>
      <Route path="/home" element={<PageHome/>}/>
      <Route path='/profile/:id' element={<PageProfile/>}/>
      <Route path="*" element={<Page404 />} />
    </>
  )
}
