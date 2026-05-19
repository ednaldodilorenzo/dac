import { createContext, useContext, useState, type ReactNode } from "react";

type AuthContextValue = {
    isAuthenticated: boolean;
    storeToken: (token: string) => void,
    clearToken: () => void,
}

const AuthContext = createContext<AuthContextValue | null>(null);

export function AuthContextProvider({ children }: { children: ReactNode }) {

    const [token, setToken] = useState("");

    function storeToken(token: string) {
        localStorage.setItem("token", token);
        setToken(token);
    }

    function clearToken() {
        localStorage.removeItem("token");
        setToken("");
    }

    const contextValue: AuthContextValue = {
        isAuthenticated: !!token,
        storeToken,
        clearToken,
    }

    return <AuthContext.Provider value={contextValue}>
        {children}
    </AuthContext.Provider>
}

export function useAuthContext(): AuthContextValue {
    return useContext(AuthContext)!;
}