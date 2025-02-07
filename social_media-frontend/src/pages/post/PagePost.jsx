import { Link, useParams } from "react-router-dom"
import { getPostById } from "../../services/postsService"
import NavBar from "../../components/NavBar"
import { useEffect, useState } from "react"

export default function PagePost(){

  const [errMsg, setErrMsg] = useState('')
  const [post, setPost] = useState(null)

  const { id } = useParams()
  useEffect(()=>{
    const getPost = async () => {
      try {
        const post = await getPostById(id)
        setPost(post)
      }catch(err){
        setErrMsg(err.message)
      }
    }
    getPost()
  },[id])

  return(
    <>
      <NavBar/>
      <main className="p-3 flex flex-col gap-2 max-w-[900px] mx-auto">
        {
          post ?(
            <>
              <Link to={`/profile/${post.userData.userId}`}>
                <header className="w-full flex gap-3 items-center py-3">
                  <img src={post.userData.avatarUrl}
                    className="w-16 rounded-full"
                  />
                  <h4 className="name-post">{post.userData.userName}</h4>
                </header>
              </Link>
              <section>
                <Link target="_blank" to={post.description.imageUrl}>
                  <img className="w-full rounded-2xl md:aspect-4/3 md:object-cover" src={post.description.imageUrl}/>
                </Link>
              </section>
              <section className="bg-primary-800 rounded-xl p-3 pt-16 -mt-16 -z-10">
                <h1 className="h1-pop">{post.description.title}</h1>
                <div className="py-2 flex gap-2 paragraph-s">
                  {
                    post.category.map((cat, index) => (
                      <span key={index} className="px-2 py-1 bg-primary-200 rounded-md">{cat}</span>
                    )
                    )
                  }
                </div> 
                <p className="content-post">{post.description.content}</p>
              </section>
            </>
          ) : (
            <>
              <div className="w-full text-center h-1/2">Post no encontrado{errMsg}</div>
            </>
          )
        }
        
      </main>
    </>
  )
}