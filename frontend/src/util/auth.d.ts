export interface UserProfile {
  id?: number | string;
  memberId?: number | string;
  leaderId?: number | string;
  firstName?: string;
  lastName?: string;
  userName?: string;
  email?: string;
  role?: string;
  phone?: string;
  altPhone?: string;
  address?: string;
  churchSite?: string;
  [key: string]: any;
}

export declare function getUser(): UserProfile | null;
export declare function getToken(): string | null;
export declare function isAuthenticated(): boolean;
export declare function getAuthHeader(): Record<string, string>;
export declare function isLeader(): boolean;
export declare function isMember(): boolean;
export declare function logout(): void;
