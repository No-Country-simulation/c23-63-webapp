import { Link } from "react-router-dom";
import { getUsersList } from "../../services/userService";
import User from "../../assets/Icons/User";

export default function ProfilesList () {

  const userList = getUsersList()

  return(
    <article className=" w-full bg-primary-800 rounded-2xl">
      <h3 className="title p-3">Amigos sugeridos</h3>
      <ul>
        {
          userList?.map((user)=>(
            <li key={user.userId}>
              <Link to={`/profile/${user.userId}`}>
                <article className="flex gap-2 items-center p-3">
                  {
                    user.avatarUrl 
                      ? <img src={user.avatarUrl} alt="Profile" className="w-10 rounded-full"/>
                      : <User className="w-10 h-10 rounded-full" />
                  }
                  <div className="grid">
                    <strong>{user.userName}</strong>
                    <span className="paragraph-s text-neutral-400">
                      {user.jobTitle}
                    </span>
                  </div>
                </article>
              </Link>
            </li>
          ))
        }
      </ul>
    </article>
  )
}