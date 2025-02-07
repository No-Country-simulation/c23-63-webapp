import userList from '../models/users_list.json'
import axiosApi from './axiosApi'
import { ERROR_MESSAGES } from './errorMessages'

export const getUsersList = () => {
  return userList
}

export const getUser = async () => {
  try {
    const res = axiosApi.get('/user-list')
    return res
  }catch (err){
    const message = err?.response 
      ? ERROR_MESSAGES[err.response.status] || ERROR_MESSAGES.default
      : ERROR_MESSAGES.connectionError
    throw new Error(message)
  }

}