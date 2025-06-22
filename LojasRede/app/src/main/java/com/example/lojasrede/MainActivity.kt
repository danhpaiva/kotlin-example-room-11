package com.example.lojasrede

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.lojasrede.repository.AppDatabase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val edtLogin = findViewById<EditText>(R.id.edtLogin)
        val edtSenha = findViewById<EditText>(R.id.edtSenha)

        val btnCadastro = findViewById<Button>(R.id.btnCadastro)

        val usuarioDatabase = AppDatabase.getDataBase(this).usuarioDAO()

        btnCadastro.setOnClickListener {
            val intent = Intent(this, CadastroActivity::class.java)
            startActivity(intent)
        }

        val btnLogin = findViewById<Button>(R.id.btnLogin)

        btnLogin.setOnClickListener {

            val login = edtLogin.text.toString().trim()
            val senha = edtSenha.text.toString().trim()

            // Validação de campos obrigatórios
            if (login.isEmpty() || senha.isEmpty()) {
                Toast.makeText(this, "Por favor, preencha o login e a senha.", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }

            // Verificar tamanho mínimo da senha
            if (senha.length < 6) {
                Toast.makeText(
                    this,
                    "A senha deve ter pelo menos 6 caracteres.",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            val usuario = usuarioDatabase.getUserByEmailAndSenha(login, senha)

            if (usuario != null) {
                // Login bem-sucedido
                val intent = Intent(this, BemVindoActivity::class.java)
                intent.putExtra("NOME_USUARIO", usuario.nome)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Login ou senha inválidos.", Toast.LENGTH_SHORT).show()
            }
        }

        btnCadastro.setOnClickListener {
            val intent = Intent(this, CadastroActivity::class.java)
            startActivity(intent)
        }
    }
}
