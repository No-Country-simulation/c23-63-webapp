import { Route } from "react-router-dom";
import PrivateRouterFilter from "./PrivateRouterFilter";
import PageHome from "../pages/home/PageHome";
export function PrivateRoutes() {
  return (
    <Route element={<PrivateRouterFilter/>}>
      <Route path="/" element={<PageHome/>}/>
    </Route>
  )
}