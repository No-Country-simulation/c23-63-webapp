import { useParams } from "react-router-dom"
import NavBar from "../../components/NavBar"
import UserInfo from "../../components/UserInfo"
import { useState } from "react"
import PostsProfile from "./PostsProfile"
import NewPost from "./NewPost"


export default function PageProfile() {

  const { id } = useParams()

  const [showCreateForm, setShowCreateForm] = useState(true);

  // const [title, setTitle] = useState('')

  return (
    <div className=" gap-4 justify-center grid-cols-1 pb-3">
      <NavBar />
      <UserInfo id={id}/>
      <main className="px-3 lg:p-0 max-w-[1200px] lg:mx-auto">      
        {
          showCreateForm 
          ? (
              <NewPost setShowCreateForm={setShowCreateForm}/>
              // <>
              //   <button onClick={()=>{setShowCreateForm(false)}} className="text-red-300 bg-red-950 p-3 rounded-full">
              //     Cerrar
              //   </button>
              //   <form>
              //     <input
              //       className="input"
              //       type="text"
              //       placeholder="Título"
              //       onChange={({target})=> setTitle(target.value)}
              //       value={title}
              //       required
              //     />
              //   </form>
              // </>
            )
          : (
              <PostsProfile id={id} setShowCreateForm={setShowCreateForm}/>
            )
        }
      </main>
      
              
    </div>
  )
}