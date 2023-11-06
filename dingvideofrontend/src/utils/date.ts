const timeUtil =  (timestamp: string, mode: 'relative' | 'date' = 'relative'): string =>  {
  const date = new Date(timestamp);

  if (mode === 'date') {
    const year = date.getFullYear();
    const month = date.getMonth() + 1;
    const day = date.getDate();
    return `${year}-${month.toString().padStart(2, '0')}-${day.toString().padStart(2, '0')}`;
  }

  const now = Date.now();
  const past = date.getTime();
  const diff = Math.floor((now - past) / 1000); // 差异，单位为秒

  if (diff < 60) {
    return `${diff} 秒钟前`;
  } else if (diff < 3600) {
    return `${Math.floor(diff / 60)} 分钟前`;
  } else if (diff < 86400) {
    return `${Math.floor(diff / 3600)} 小时前`;
  } else if (diff < 604800) {
    return `${Math.floor(diff / 86400)} 天前`;
  } else if (diff < 2592000) {
    return `${Math.floor(diff / 604800)} 周前`;
  } else if (diff < 31536000) {
    return `${Math.floor(diff / 2592000)} 个月前`;
  } else {
    return `${Math.floor(diff / 31536000)} 年前`;
  }
}
export default timeUtil;