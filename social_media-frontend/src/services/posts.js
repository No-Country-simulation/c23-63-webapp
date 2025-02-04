import axiosApi from './axiosApi';

export const createPost = async (postData) => {
  try {
    const response = await axiosApi.post('/posts/create', postData)
    return response.data
  } catch (err){
    console.error("Error al crear el post:", err);
    throw err
  }
}