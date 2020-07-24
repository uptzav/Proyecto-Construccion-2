<?php

use App\Model\Usuario;
use Carbon\Carbon;
use Illuminate\Database\Seeder;
use Faker\Factory as Faker;

class UsuarioSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        $faker = Faker::create();
        $obj = new Usuario();
        $obj->nombre = "Fiorella";
        $obj->email = "Fiorella@gmail.com";
        $obj->password = bcrypt('123123');
        $obj->estado = 1;
        $obj->save();
    }
}
