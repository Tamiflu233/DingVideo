// auth.ts
// @ts-ignore
import Cookies from 'js-cookie';
const TokenKey = 'asr-token';
export const getToken = () => Cookies.get(TokenKey);
export const delToken = () => Cookies.remove(TokenKey);