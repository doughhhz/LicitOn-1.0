import React, { useState } from 'react';
import api from '../services/api';

const Auth = () => {
  const [isLogin, setIsLogin] = useState(true);
  const [formData, setFormData] = useState({
    nomeCompleto: '', cpf: '', email: '', senha: '', telefone: '', perfil: 'TRIAGEM', termosAceitos: false
  });

  const handleChange = (e) => {
    const { name, value, type, checked } = e.target;
    setFormData({ ...formData, [name]: type === 'checkbox' ? checked : value });
  };

  const handleCadastro = async (e) => {
    e.preventDefault();
    try {
      await api.post('/usuarios', formData);
      alert('Usuário cadastrado com sucesso!');
      setIsLogin(true);
    } catch (error) {
      alert('Erro ao cadastrar: ' + error.response.data.message);
    }
  };

  return (
    <div className="min-h-screen flex items-center justify-center bg-gray-100 p-4">
      <div className="bg-white p-8 rounded-lg shadow-md w-full max-w-md">
        <h2 className="text-2xl font-bold mb-6 text-center text-blue-600">
          {isLogin ? 'Login LicitON' : 'Criar Conta no LicitON'}
        </h2>

        <form onSubmit={!isLogin ? handleCadastro : undefined} className="space-y-4">
          {!isLogin && (
            <>
              <input type="text" name="nomeCompleto" placeholder="Nome Completo" className="w-full p-2 border rounded" onChange={handleChange} required />
              <input type="text" name="cpf" placeholder="CPF" className="w-full p-2 border rounded" onChange={handleChange} required />
              <input type="text" name="telefone" placeholder="WhatsApp" className="w-full p-2 border rounded" onChange={handleChange} />
              
              <select name="perfil" className="w-full p-2 border rounded" onChange={handleChange}>
                <option value="TRIAGEM">Analista de Triagem</option>
                <option value="COTADOR">Cotador</option>
                <option value="ADMINISTRADOR">Administrador</option>
              </select>
            </>
          )}

          <input type="email" name="email" placeholder="E-mail (ex: @empresa.com)" className="w-full p-2 border rounded" onChange={handleChange} required />
          <input type="password" name="senha" placeholder="Senha" className="w-full p-2 border rounded" onChange={handleChange} required />

          {!isLogin && (
            <label className="flex items-center space-x-2 text-sm">
              <input type="checkbox" name="termosAceitos" onChange={handleChange} required />
              <span>Aceito os Termos e Privacidade</span>
            </label>
          )}

          <button type="submit" className="w-full bg-blue-600 text-white p-2 rounded hover:bg-blue-700 transition">
            {isLogin ? 'Entrar' : 'Finalizar Cadastro'}
          </button>
        </form>

        <button onClick={() => setIsLogin(!isLogin)} className="w-full mt-4 text-sm text-gray-500 hover:underline">
          {isLogin ? 'Não tem conta? Cadastre-se' : 'Já tem conta? Faça login'}
        </button>
      </div>
    </div>
  );
};

export default Auth;