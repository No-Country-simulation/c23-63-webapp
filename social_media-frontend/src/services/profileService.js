import profiles from '../models/profiles.json'
// import posts from '../models/posts_profile.json'
import axiosApi from './axiosApi'
import { ERROR_MESSAGES } from './errorMessages'

export const getProfileById = ( idParam, sessionId) => {
  console.log(sessionId)
  const profile = profiles.profiles.find(
    (currentProfile) => currentProfile.userId.toString() === idParam.toString()
  )
  return profile || null
}

// export const getProfileById = async (id, sessionId) => {
//   try {
//     return await axiosApi.get(`profile/${id}`,{sessionId})
//   }catch(e) {
//     const message = e?.response
//       ? ERROR_MESSAGES[e.response.status] || ERROR_MESSAGES.default
//       : ERROR_MESSAGES.connectionError
//     throw new Error(message)
//   }
// }

// export const getPostsByProfileId = (id) => {
//   const postsProfile = posts.find(
//     (current) => current.userId.toString() === id.toString()
//   )
//   if(postsProfile) {
//     return postsProfile
//   }else {
//     throw new Error("No hay datos")
//   }
// }

export const getPostsByProfileId = async (id) => {
  try {
    const response = await axiosApi.get(`api/posts/user/${id}`)
    return response.data
  }catch(e){
    const message = e?.response
      ? ERROR_MESSAGES[e.response.status] || ERROR_MESSAGES.default
      : ERROR_MESSAGES.connectionError
    throw new Error(message)
  }
}

export const followUser = async (friendId, sessionId, state) => {
  try {
    return state
      ? await axiosApi.post("/follow", { sessionId, friendId })
      : await axiosApi.delete(`/follow/${sessionId}/${friendId}`)
  } catch (e) {
    const message = e?.response
      ? ERROR_MESSAGES[e.response.status] || ERROR_MESSAGES.default
      : ERROR_MESSAGES.connectionError;
    throw new Error(message);
  }
};