import { useEffect, useState } from "react";
import IconNewProject from "../../assets/Icons/IconNewProject";

import { useAuthContext } from "../../context/AuthContext";
import { getPostsByProfileId } from "../../services/profileService";
import { Link } from "react-router-dom";

export default function PostsProfile({id , setShowCreateForm}){
  const [posts, setPosts] = useState([]);
  const [countPosts, setCountPosts] = useState(0);
  const [loading, setLoading] = useState(true);
  const [errorMsg, setErrorMsg] = useState('')

  const {user, isAuthenticated} = useAuthContext()

  useEffect(()=>{
    const getData = async () => {
      try {
        const { posts, countPosts } = await getPostsByProfileId(id);
        // console.log(posts)
        setPosts(posts);
        setCountPosts(countPosts);
      } catch (error) {
        setErrorMsg(error.message)
      } finally {
        setLoading(false);
      }
    }

    getData()
  },[id])

  if(loading){
    return (
      <div className="w-full h-96 flex justify-center items-center">
        <div className="loader"></div>
      </div>
    )
  }

  return(
    <>
      {
        errorMsg 
        ? (
            <p className="w-full text-center text-white/50 py-8">{errorMsg}</p>
          )
        : (
            <> 
            <header className="flex gap-2">
              <p className="paragraph p-2">
                {
                  countPosts 
                    ? `${countPosts} publicaciones`
                    : 'No hay publicaciones'
                }
              </p>
            </header>
            <section className="grid gap-3 md:grid-cols-2 lg:grid-cols-3">
              {
                (isAuthenticated && user.id.toString() === id) && <button 
                  className="bg-primary-800 rounded-xl col-span-1 flex flex-col gap-2 justify-center items-center min-h-64"
                  onClick={()=>{setShowCreateForm(true)}}
                >
                  <IconNewProject/>
                  <span className="paragraph">Agregar nuevo post</span>
                </button>
              }
              {
                posts && posts.map((post, index) => (
                  <article key={index} className="">
                    <Link to={`/post/${post.postId}`}>
                      <figure>
                        <img src={post.description.imageUrl} alt="Post"
                          className="w-full aspect-2/1 object-cover rounded-xl"
                        />
                      </figure>
                      <section className="bg-primary-800 -mt-4 pt-8 p-4 rounded-xl min-h-24 grid items-center">
                        <p className="paragraph">
                          {post.description.title}
                        </p>
                        {
                          post.category && <p className="paragraph-s">{post.category}</p>
                        }
                      </section>
                    </Link>
                  </article>
                ))
              }
            </section>
            </>
          )
      }
    </>
  )
}