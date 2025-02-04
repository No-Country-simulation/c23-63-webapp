import { useState } from "react"
import { useParams } from "react-router-dom"

import NavBar from "../../components/NavBar"
import UserInfo from "../../components/UserInfo"
import CreateNewPost from "./CreateNewPost"
import PostsProfile from "./PostsProfile"


export default function PageProfile() {

  const { id } = useParams()

  const [showCreateForm, setShowCreateForm] = useState(false);


  return (
    <div className=" gap-4 justify-center grid-cols-1 pb-3">
      <NavBar/>
      <UserInfo idParam={id}/>
      <main className="px-3 max-w-[1200px] lg:mx-auto min-h-96">      
        {
          showCreateForm 
          ? (
              <CreateNewPost setShowCreateForm={setShowCreateForm}/>
            )
          : (
              <PostsProfile id={id} setShowCreateForm={setShowCreateForm}/>
            )
        }
      </main>             
    </div>
  )
}