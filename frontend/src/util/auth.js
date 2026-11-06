const read = () => localStorage.getItem('user') || sessionStorage.getItem('user')

export function getUser() {
  try {
    const val = read()
    return val ? JSON.parse(val) : null
  } catch {
    return null
  }
}

export function getToken() {
  return localStorage.getItem('token') || sessionStorage.getItem('token')
}

export function isAuthenticated() {
  return Boolean(getToken() && getUser())
}

export function getAuthHeader() {
  const t = getToken()
  return t ? { Authorization: `Bearer ${t}` } : {}
}

export function isLeader() {
  const r = getUser()?.role
  return r === 'LEADER' || r === 'ADMIN'
}

export function isMember() {
  const r = getUser()?.role
  return r === 'MEMBER'
}

export function logout() {
  localStorage.removeItem('token')
  localStorage.removeItem('user')
  sessionStorage.removeItem('token')
  sessionStorage.removeItem('user')
}

